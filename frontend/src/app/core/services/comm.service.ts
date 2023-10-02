import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ICommRequest } from '@comm/models/ICommRequest';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommService {
  // Properties
  apiUrl: string = "http://localhost:8080/api/comm";

  // Constructor
  constructor(private http: HttpClient) { }

  // Lifecycle Hooks
  ngOnInit(): void {
    
  }

  // Methods
  getComms(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  getComm(id: Number): Observable<any> {
    return this.http.get(this.apiUrl + `/${id}`);
  }

  addComm(commRequest: ICommRequest): Observable<any> {
    let headers = new HttpHeaders();
    headers = headers.set('Content-Type', 'application/json');
    return this.http.post(this.apiUrl, commRequest, {headers});
  }

  updateComm(id: Number, commRequest: ICommRequest): Observable<any> {
    let headers = new HttpHeaders();
    headers = headers.set('Content-Type', 'application/json');
    return this.http.put(this.apiUrl + `/${id}`, commRequest, {headers});
  }
}
