import React, { useState } from 'react';
import DatesRange from './dates_range';
import ShowPdf from './show_pdf';
export default function Report() {
    const onDownloadPdf = (dateRage) => {
            var url =  `http://localhost:8080/api/transactions/report/${dateRage}`;
                setAPIData([url]);
      };

      const [APIData, setAPIData] = useState([]);

    return (
        <div className='container-read'>
            <h2 className='title'>Reporte</h2>
            <DatesRange onDownloadPdf={onDownloadPdf}/>
            
            {APIData.map((url) => {
                            return (       <ShowPdf className='center' url={url} />
                           )
                        })}
        </div>
    )
}