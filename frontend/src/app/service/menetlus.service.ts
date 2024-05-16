import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Menetlus } from '../model/menetlus';
import { CustomResponse } from '../model/custom-response';

@Injectable({
  providedIn: "root"
})
export class MenetlusService {
  private readonly url = environment.apiUrl + "menetlus"

  constructor(private http: HttpClient) {}

  getAll(): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(this.url);
  }

  getById(id: number): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`this.url/${id}`);
  }

  saveMenetlus(menetlus: Menetlus): Observable<CustomResponse> {
    return this.http.post<CustomResponse>(this.url, menetlus);
  }

  validateForm(menetlus: Menetlus): boolean {
    let emailRegex = new RegExp(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)
    if (menetlus.name.trim().length < 1) {
      alert("Isiku nimi on kohustuslik!");
      return false;
    }
    if (menetlus.personalCode == null) {
      alert("Isikukoodi väli on tühi või sisaldab tähemärki! Ainult numbrid on lubatud!")
      return false;
    }
    if (menetlus.personalCode.toString().length !== 11) {
      alert("Isikukood peab olema 11 numbrit!");
      return false;
    }
    if (!emailRegex.test(menetlus.email)) {
      alert("E-kirja formaat on vale!");
      return false;
    }
    return true;
  }
}
