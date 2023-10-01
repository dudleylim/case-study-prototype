import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
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
}
