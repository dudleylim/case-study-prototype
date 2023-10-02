import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { CommClassService } from '@core/services/comm-class.service';
import { ICommClass } from '@core/models/ICommClass';
import { EMPTY, catchError } from 'rxjs';
import { ActivatedRoute, ParamMap } from '@angular/router';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {
  commClass?: ICommClass;

  id: Number = 0;
  error: boolean = false;
  errorMessage: String = "";

  name: String = "";
  description: String = "";
  serviceType: String = "";
  status: String = "";
  statusReason: String = "";

  constructor(private commClassService: CommClassService, private location: Location, private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.id = +Number(params.get('id'));
    });

    if (Number.isNaN(this.id)) {
      this.error = true;
    } else {
      this.commClassService.getCommClass(this.id)
      .pipe(catchError((error) => {
        console.log(error.error.message);
        this.error = true;
        this.errorMessage = error.error.message;
        return EMPTY;
      }))
      .subscribe({
        next: (response) => {
          this.commClass = response;

          this.setName(response.commClassName);
          response.commClassDescription == null ? this.setDescription("") : this.setDescription(response.commClassDescription);
          response.statusReason == null ? this.setStatusReason("") : this.setStatusReason(response.setStatusReason); 
          this.setStatus(response.commClassStatus);
          this.setServiceType(response.serviceType);
        }
      });
    }
  }

  update(): void {
    this.commClass!.commClassName = this.name;
    this.commClass!.commClassDescription = this.description;
    this.commClass!.commClassStatus = this.status;
    this.commClass!.serviceType = this.serviceType;
    this.commClass!.commClassStatusReason = this.statusReason;

    this.commClassService.updateCommClass(this.commClass!)
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

  setStatus(status: String): void {
    this.status = status;
  }

  setStatusReason(statusReason: String): void {
    this.statusReason = statusReason;
  }
}
