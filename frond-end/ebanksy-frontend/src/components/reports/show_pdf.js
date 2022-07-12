
import React, { useState } from 'react';
import { Document, Page, pdfjs } from "react-pdf";

const ShowPdf =  ({
    url
  }) => {
      
    const [numPages, setNumPages] = useState(null);
  const [pageNumber, setPageNumber] = useState(1);

  function onDocumentLoadSuccess({ numPages }) {
    setNumPages(numPages);
  }
  pdfjs.GlobalWorkerOptions.workerSrc = `//cdnjs.cloudflare.com/ajax/libs/pdf.js/${pdfjs.version}/pdf.worker.js`;

  return (
    <div>
      <Document
        file={url}
        onLoadSuccess={onDocumentLoadSuccess}
      >
        <Page
          pageNumber={pageNumber}
        />
      </Document>
    </div>
  );
}
export default ShowPdf;