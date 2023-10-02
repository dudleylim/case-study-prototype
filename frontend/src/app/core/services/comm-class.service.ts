import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { ICommClass } from '@core/models/ICommClass';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommClassService implements OnInit {
  // Properties
  apiUrl: string = "http://localhost:8080/api/comm_class";

  // Constructor
  constructor(private http: HttpClient) { }

  // Lifecycle Hooks
  ngOnInit(): void {
    
  }

  // Methods
  getCommClasses(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  getCommClass(id: Number): Observable<any> {
    return this.http.get(this.apiUrl + `/${id}`);
  }

  addCommClass(commClass: ICommClass): Observable<any> {
    let headers = new HttpHeaders();
    headers = headers.set('Content-Type', 'application/json');
    return this.http.post(this.apiUrl, commClass, {headers});
  }

  updateCommClass(commClass: ICommClass): Observable<any> {
    let headers = new HttpHeaders();
    headers = headers.set('Content-Type', 'application/json');
    return this.http.put(this.apiUrl + `/${commClass.commClassId}`, commClass, {headers});
  }
}
