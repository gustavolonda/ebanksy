import React from 'react';
import "./search.css"
const Search = ({onChange
      }) => {
              return (
                <div className="form-outline">
                   
                      < input
                        className="form-control"
                        type="text"
                        name="query"
                        id="search-input"
                        placeholder="Buscar..."
                        onChange={(evt) => {
                        
                          onChange(evt.target.value)
                        }}
                      />
                      
           
                  </div>
              )
      
      };

      export default Search;