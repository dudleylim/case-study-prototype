import { Component } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { ICommRequest } from '@comm/models/ICommRequest';
import { IComm } from '@core/models/IComm';
import { ICommClass } from '@core/models/ICommClass';
import { CommClassService } from '@core/services/comm-class.service';
import { CommService } from '@core/services/comm.service';
import { EMPTY, catchError } from 'rxjs';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent {
  comm?: IComm;

  id: Number = 0;
  error: boolean = false;
  errorMessage: String = "";

  name: String = "";
  description: String = "";
  commClassId: Number = 0;
  status: String = "";
  statusReason: String = "";
  commClasses: ICommClass[] = [];

  constructor(private commService: CommService, private commClassService: CommClassService, private location: Location, private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.getCommClasses();

    this.route.paramMap.subscribe((params: ParamMap) => {
      this.id = +Number(params.get('id'));
    });

    if (Number.isNaN(this.id)) {
      this.error = true;
    } else {
      this.commService.getComm(this.id)
      .pipe(catchError((error) => {
        console.log(error.error.message);
        this.error = true;
        this.errorMessage = error.error.message;
        return EMPTY;
      }))
      .subscribe({
        next: (response) => {
          this.comm = response;

          this.setName(response.commName);
          response.commDescription == null ? this.setDescription("") : this.setDescription(response.commDescription);
          response.statusReason == null ? this.setStatusReason("") : this.setStatusReason(response.setStatusReason); 
          this.setStatus(response.commStatus);
          this.setCommClassId(String(response.commClass.commClassId));
        }
      });
    }
  }

  update(): void {
    const newComm: ICommRequest = {
      commName: this.name,
      commDescription: this.description,
      commClassId: this.commClassId,
      commStatus: this.status,
      commStatusReason: this.statusReason
    }

    this.commService.updateComm(this.id, newComm)
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

  getCommClasses(): void {
    this.commClassService.getCommClasses().subscribe({
      next: (response) => {
        this.commClasses = response.filter((commClass: ICommClass) => {
          return commClass.commClassStatus == "ACTIVE";
        });
      }
    });
  }

  setName(name: String): void {
    this.name = name;
  }

  setDescription(description: String): void {
    this.description = description;
  }

  setCommClassId(commClassId: String): void {
    this.commClassId = +commClassId;
  }

  setStatus(status: String): void {
    this.status = status;
  }

  setStatusReason(statusReason: String): void {
    this.statusReason = statusReason;
  }

  log() {
    console.log(this.commClassId);
  }
}
