package com.iverno.gus.transaction.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.iverno.gus.commons.general.application.bo.TransactionBO;
import com.iverno.gus.commons.general.application.bo.TransactionTypeDomain;
import com.iverno.gus.commons.general.application.dto.AccountDTO;
import com.iverno.gus.commons.general.application.exception.BaseException;
import com.iverno.gus.commons.general.application.service.EndPointServiceImpl;
import com.iverno.gus.commons.general.domain.entities.FieldHead;
import com.iverno.gus.commons.general.domain.entities.GeneratorPDF;
import com.iverno.gus.commons.general.domain.model.ResponseBase;

import static com.iverno.gus.commons.general.config.Constants.UNEXPECTED_ERROR;
import static com.iverno.gus.commons.general.domain.model.ResponseBaseStatusDomain.ERROR;
import static com.iverno.gus.config.Constants.*;

import static com.iverno.gus.transaction.application.adapter.TransactionAdapter.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import com.iverno.gus.transaction.application.dto.TransactionDTO;
import com.iverno.gus.transaction.application.dto.TransactionHead;
import com.iverno.gus.transaction.application.openfeign.AccountOpenFeignServiceImpl;
import com.iverno.gus.transaction.application.validate.TransactionValidate;
import com.iverno.gus.transaction.domain.entity.TransactionEntity;
import com.iverno.gus.transaction.domain.repository.TransactionRespository;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import lombok.SneakyThrows;

@Service
@Qualifier("transactionService")
public class TransactionService extends EndPointServiceImpl< TransactionDTO, TransactionBO , TransactionEntity, String>{
	private  String className = this.getClass().getSimpleName(); 
	int columnNum=0;
	@Autowired
	TransactionRespository repository;
	@Autowired
	AccountOpenFeignServiceImpl accountOpenFeignServiceImpl;
	@Autowired
	TransactionValidate transactionValidate;
	@Override
	public JpaRepository<TransactionEntity, String> getDao() {
		return repository;
	}
	@Override
	public TransactionEntity runCreate(TransactionEntity entity) {
		AccountDTO  accountDTO = accountOpenFeignServiceImpl.getAccountById(entity.getAccountId());
		if(entity.getValue() > 0 && entity.getTransactionType().equalsIgnoreCase(TransactionTypeDomain.WITHDRAW))
			entity.setValue(-entity.getValue());
		double availableBalance = accountDTO.getAvailableBalance() + entity.getValue();
		AccountDTO  accountDTOResult = accountOpenFeignServiceImpl.availableBalanceUpdate(entity.getAccountId(), availableBalance);
		entity.setAvailableBalance(availableBalance);
		entity.setActive(true);
		return entity;
	}
	@Override
	public TransactionEntity runUpdate(TransactionEntity entity) {
		TransactionEntity transactionEntity = this.getById(entity.getId());
		entity.setCreateDate(transactionEntity.getCreateDate());
		transactionEntity.setStatus(entity.isStatus());
		return transactionEntity;
	}
	@Override
	public TransactionEntity statusChangeDelete(TransactionEntity entity) {
		entity.setActive(false);
		return entity;
	}
	@Override
	public TransactionEntity modelBOToEntity( Object modelBO) {
		
		return transactionBOToTransactionEntity((TransactionBO)modelBO);
	}
	@SneakyThrows
	@Override
	public TransactionDTO entityToModelDTO(TransactionEntity entity) {
		
		try{
			TransactionDTO transactionDTO = transactionEntityToTransactionDTO(entity);
			if(entity.getAccountId() != null && !entity.getAccountId().isEmpty()) {
				AccountDTO accountDTO = accountOpenFeignServiceImpl.getAccountById(entity.getAccountId());
				if (accountDTO != null) 
					transactionDTO = transactionEntityToTransactionDTO(entity, accountDTO);

			}
			return transactionDTO;
		}catch (Exception e){
	        
        	throw e;
        }
	}
	@Override
	public String nameModule() {
		return MODULE_TRASACTION;
	}
	@Override
	public String className() {
		return className;
	}
	@Override
	public BaseException validateBeforeSave(TransactionEntity entity) {
		return transactionValidate.validateBeforeSave(entity);
	}
	@SneakyThrows
	public ResponseBase<?> getListActive() {
		
		try{
			List<TransactionEntity> transactionDTOList = this.repository.findAllByActiveOrderByModifyDateDesc(true);
			
			return toResponseBase(transactionDTOList);
		}catch (Exception e){
	        
			throw new BaseException().builder()
			.status(ERROR)
			.message(UNEXPECTED_ERROR)
			.module(nameModule())
			.exception(e)
			.build();
        }
	}
	@SneakyThrows
	public ResponseBase<?> getBySearchText(String searchText) {
		
		try{
			List<TransactionEntity> transactionDTOList = new ArrayList<>();
			List<AccountDTO> accountDTOList = accountOpenFeignServiceImpl.getBySearchText(searchText);
			if(accountDTOList.size() > 0) {
				List<String> accountIdList = accountDTOList.stream().map(a -> a.getId()).collect(Collectors.toList());
				transactionDTOList = this.repository.findDistinctByActiveAndAccountIdIn(true, accountIdList);
	
			}
			
			return toResponseBase(transactionDTOList);
		}catch (Exception e){
	        
			throw new BaseException().builder()
			.status(ERROR)
			.message(UNEXPECTED_ERROR)
			.module(nameModule())
			.exception(e)
			.build();
        }
	}
	
	@SneakyThrows
	public void exportPdf(HttpServletResponse response, String dateRange) {
		
		try{
			 response.setContentType("application/pdf");
			  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
			  String currentDateTime = dateFormat.format(new Date());
			  String headerkey = "Content-Disposition";
			  String headervalue = "attachment; filename=pdf_"+currentDateTime+".pdf";
			  response.setHeader(headerkey, headervalue);
			  // Create the Object of Document
			  Document document = new Document(PageSize.A4);
				  // get the document and write the response to output stream
			  PdfWriter.getInstance(document, response.getOutputStream());
			  columnNum = 0;
			  List<FieldHead> headList =  TransactionHead.exportHeaderEmun.toList().stream().map(n -> {
				  columnNum++;
				  return  new FieldHead().builder().num(columnNum).title(n.toString()).build();
			  }).collect(Collectors.toList());
			  if(dateRange!= null && !dateRange.isEmpty()) {
				  String[] dateRangeArray = dateRange.split("to");
				  if(dateRangeArray.length > 0) {
					  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

					  String startDateString = dateRangeArray[0];
					  String endDateString   = dateRangeArray[1];
					  Date startDate = formatter.parse(startDateString);
					  Date endDate   = formatter.parse(endDateString);
					  String subtitle = "Desde: "+startDateString+" Hasta: "+endDateString;
					  PdfPTable tableMessage = new PdfPTable(2);
					  tableMessage.setHorizontalAlignment(PdfPTable.ALIGN_RIGHT);
					 
					  
					 
					  
					  List<List<String>>pdfBodyList = this.repository.findByCreateDateBetween(startDate, endDate).stream().map(t -> {
						  		List<String> rowList = new ArrayList<>();
						  TransactionHead.exportHeaderEmun.toList()
			  												.forEach(h -> {
			  					
			  														rowList.add(h.getExportValue(h, entityToModelDTO(t)));
			  					
			  				}); 
						  return rowList;				  
					  }).collect(Collectors.toList());
						GeneratorPDF.create( document, subtitle, headList, pdfBodyList,"REPORTE DE MOVIMIENTOS");

				  }
				 

			  }
			  
			

		}catch (Exception e){
	        
			throw new BaseException().builder()
			.status(ERROR)
			.message(UNEXPECTED_ERROR)
			.module(nameModule())
			.exception(e)
			.build();
        }
	}
}
