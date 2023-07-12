import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Html} from "../model/html";

@Injectable({
  providedIn: 'root'
})
export class LabelService {
  constructor(private http:HttpClient) {}
  private url:string = "http://localhost:8080/label"

  findAll(): Observable<any>{
    return this.http.get(this.url);
  }

    sendHtml(html: Html): Observable<any>{
    return this.http.post(this.url+"/replace", html);
  }
}
