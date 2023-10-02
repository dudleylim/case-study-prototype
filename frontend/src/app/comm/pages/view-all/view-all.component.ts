import { Component } from '@angular/core';
import { IComm } from '@core/models/IComm';
import { CommService } from '@core/services/comm.service';

@Component({
  selector: 'app-view-all',
  templateUrl: './view-all.component.html',
  styleUrls: ['./view-all.component.css']
})
export class ViewAllComponent {
  comms: IComm[] = [];
  searchedComms: IComm[] = [];
  filteredComms: IComm[] = [];
  finalComms: IComm[] = [];
  searchValue: string = "";
  filterValue: string = "all";

  constructor(private commService: CommService) {
    
  }

  ngOnInit(): void {
    this.getComms();
  }

  getComms(): void {
    this.commService.getComms().subscribe({
      next: (response) => {
        this.comms = response;
        this.filteredComms = response;
        this.searchedComms = response;
      }
    });
  }

  search(value: string) {
    this.searchValue = value;
    if (this.searchValue.length > 2) {
      this.searchedComms = this.comms.filter((comm) => {
        return comm.commName?.toLowerCase().includes(this.searchValue.toLowerCase());
      })
    } else if (this.searchValue.length == 0) {
      this.searchedComms = this.comms;
    }
    this.filter(this.filterValue);
  }

  filter(value: string) {
    this.filterValue = value;
    this.filteredComms = this.searchedComms.filter((comm) => {
      if (this.filterValue == "all") {
        return comm;
      } else {
        return comm.commStatus?.toLowerCase() == this.filterValue.toLowerCase();
      }
    });
  }
}
