import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { IComm } from '@core/models/IComm';
import { CommService } from '@core/services/comm.service';
import { CommClassService } from '@core/services/comm-class.service';
import { ICommClass } from '@core/models/ICommClass';
import { ICommRequest } from '@comm/models/ICommRequest';
import { EMPTY, catchError } from 'rxjs';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {
  name: String = "";
  description: String = "";
  commClassId: Number = 0;
  commClasses: ICommClass[] = [];

  constructor(private commService: CommService, private commClassService: CommClassService, private location: Location) {

  }

  ngOnInit(): void {
    this.getCommClasses();
  }

  add(): void {
    const newComm: ICommRequest = {
      commName: this.name,
      commDescription: this.description,
      commClassId: this.commClassId
    }

    this.commService.addComm(newComm)
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

  log() {
    console.log(this.commClassId);
  }
}
