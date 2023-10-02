import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { ICommClass } from '@core/models/ICommClass';
import { CommClassService } from '@core/services/comm-class.service';
import { EMPTY, catchError, throwError } from 'rxjs';

@Component({
  selector: 'app-view-specific',
  templateUrl: './view-specific.component.html',
  styleUrls: ['./view-specific.component.css']
})
export class ViewSpecificComponent implements OnInit {
  id: Number = 0;
  error: boolean = true;
  errorMessage: String = "";
  commClass?: ICommClass;

  constructor(private route: ActivatedRoute, private commClassService: CommClassService, private location: Location) {

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
          this.error = false;
          this.commClass = response;
        }
      });
    }
    // this.id = Number(this.route.snapshot.paramMap.get('id'));
  }

  back(): void {
    this.location.back();
  }
}
