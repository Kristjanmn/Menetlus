import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Menetlus } from '../model/menetlus';

@Injectable({
  providedIn: "root"
})
export class MenetlusService {
  private readonly url = environment.apiUrl + "menetlus"

  constructor(private http: HttpClient) {}

  getAll(): Observable<Menetlus[]> {
    return this.http.get<Menetlus[]>(this.url);
  }

  getById(id: number): Observable<Menetlus> {
    return this.http.get<Menetlus>(`this.url/${id}`);
  }

  newMenetlus(menetlus: Menetlus): void {
    this.http.post(this.url, menetlus);
  }
}
