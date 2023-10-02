import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { ICommClass } from '@core/models/ICommClass';
import { CommClassService } from '@core/services/comm-class.service';
import { EMPTY, catchError } from 'rxjs';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent {
  name: String = "";
  description: String = "";
  serviceType: String = "";

  constructor(private commClassService: CommClassService, private location: Location) {

  }

  add(): void {
    const newCommClass: ICommClass = {
      commClassName: this.name,
      commClassDescription: this.description,
      serviceType: this.serviceType
    }

    this.commClassService.addCommClass(newCommClass)
    .pipe(catchError((error) => {
      let temp = "";
      if (Array.isArray(error.error.message)) {
        for (let msg of error.error.message) {
          temp += msg;
          temp += "\n";
        }
      } else {
        temp = error.error.message;
      }
      alert(temp);
      return EMPTY;
    }))
    .subscribe({
      next: (response) => {
        alert(response.message);
        this.back();
      }
    });
  }

  back(): void {
    this.location.back();
  }

  setName(name: String): void {
    this.name = name;
  }

  setServiceType(serviceType: String): void {
    this.serviceType = serviceType;
  }

  setDescription(description: String): void {
    this.description = description;
  }

  log() {
    console.log("log");
  }
}
