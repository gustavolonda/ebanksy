import React, { useState } from "react";
import DatePicker from "react-datepicker";
import Button from 'react-bootstrap/Button'
import './dates_range.css'

import "react-datepicker/dist/react-datepicker.css";
import Moment from 'moment';
const DatesRange =  ({
  onDownloadPdf
}) => {
  const onDownloadResport = (startDate, endDate) => {
    Moment.locale('en');
    var startDateString = Moment(startDate).format('yyyy-MM-DD')+" 00:00:00";
    var endDateString = Moment(endDate).format('yyyy-MM-DD')+" 23:59:59";
    var dateRage = startDateString+"to"+endDateString;
    console.log(dateRage)
    onDownloadPdf(dateRage);
  
}
  const [startDate, setStartDate] = useState(new Date());
  const [endDate, setEndDate]   = useState( new Date());
  return (
            <div className="row">
               <div className="col-sm-4">
                  <div className="mb-3">
                    <label className="form-label">Buscar</label>
                    <DatePicker className="form-control" selected={startDate} onChange={(date) => setStartDate(date)} />
                  </div>
              </div>
              <div className="col-sm-4">
                  <div className="mb-3">
                    <label className="form-label">Hasta</label>
                    <DatePicker className="form-control"  selected={endDate} onChange={(date) => setEndDate(date)} />
                  </div>

              </div>
              <div className="col-sm-4 date-rage-button">
                    <Button className="btn btn-primary buttun-new"  onClick={() => onDownloadResport(startDate, endDate)}>Descargar</Button>
              </div>
              
            </div>
  );
};
export default  DatesRange ;