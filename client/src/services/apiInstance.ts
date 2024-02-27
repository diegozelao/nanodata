import axios from 'axios';

export const apiInstance = axios.create({
  baseURL: 'http://localhost:8080',
  headers: {
    'Content-Type': 'application/json',
    Accept: 'application/json',
    'Access-Control-Allow-Origin': '*'
  }
})

export const xmlInstance = axios.create({
  baseURL: 'http://localhost:8080',
  headers: {
    'Content-Type': 'multipart/form-data',
    Accept: 'multipart/form-data',
    'Access-Control-Allow-Origin': '*'
  }
})
