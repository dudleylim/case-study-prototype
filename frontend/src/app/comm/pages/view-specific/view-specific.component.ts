import { Component } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { IComm } from '@core/models/IComm';
import { CommService } from '@core/services/comm.service';
import { EMPTY, catchError } from 'rxjs';

@Component({
  selector: 'app-comm-view-specific',
  templateUrl: './view-specific.component.html',
  styleUrls: ['./view-specific.component.css']
})
export class ViewSpecificComponent {
  id: Number = 0;
  error: boolean = false;
  errorMessage: String = "";
  comm?: IComm;

  constructor(private route: ActivatedRoute, private commService: CommService, private location: Location) {

  }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.id = Number(params.get('id'));
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
        }
      });
    }
    // this.id = Number(this.route.snapshot.paramMap.get('id'));
  }

  back(): void {
    this.location.back();
  }
}
