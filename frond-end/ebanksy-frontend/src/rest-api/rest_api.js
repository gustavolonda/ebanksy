import queryString from 'query-string';


export default async function fetchData( url = '', type = '', data = {}, params, { setLoading }) {
  let response = {};
  let queryParams = ''
  if(params) {
    queryParams = `?${queryParams}${ queryString.stringify(params)}`
  }

  try {

  setLoading(true);
  response = await fetch(`${url}${queryParams}`, {
    method: type, // *GET, POST, PUT, DELETE, etc.
    mode: 'no-cors', // no-cors, *cors, same-origin
    cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
    credentials: 'same-origin', // include, *same-origin, omit
    headers:  type === 'GET' ? { 'Content-Type': 'application/x-www-form-urlencoded'} : {
        'Access-Control-Allow-Origin':'*',
      'Content-Type': 'application/json;charset=utf-8',
      // 'Content-Type': 'application/x-www-form-urlencoded',
   
      },
    redirect: 'follow', // manual, *follow, error
    referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
    body: type === 'GET' ? '' : JSON.stringify(data) 
  });

  setLoading(false);
    if(response.ok) {
      return response.body; // parses JSON response into native JavaScript objects
    } else {
      return {};
    }
    
  } catch (err) {
    console.error(err);
    return response.json();
  } finally {
    return response.json();
  }
}


export async function getData( url = '', params, { setLoading }) {
  let response = {};
  let queryParams = ''
  if(params) {
    queryParams = `?${queryParams}${ queryString.stringify(params)}`
  }

  try {
 
  setLoading(true);
  response = await fetch(`${url}${queryParams}`, {
    method: 'GET',
    mode: 'cors',
    cache: 'no-cache',
    credentials: 'same-origin', // include, *same-origin, omit
    headers: {
      'Content-Type': 'application/json',
      Authorization: ''
    },
    redirect: 'follow',
    referrerPolicy: 'no-referrer',
  });

  setLoading(false);
    if(response.ok) {
      return response.body; // parses JSON response into native JavaScript objects
    } else {
      return {};
    }
    
  } catch (err) {
    console.error(err);
    return response.json();
  } finally {
    return response.json();
  }
}

