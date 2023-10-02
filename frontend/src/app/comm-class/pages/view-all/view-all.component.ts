import { Component, DoCheck, OnInit } from '@angular/core';
import { ICommClass } from '@core/models/ICommClass';
import { CommClassService } from '@core/services/comm-class.service';

@Component({
  selector: 'app-view-all',
  templateUrl: './view-all.component.html',
  styleUrls: ['./view-all.component.css']
})
export class ViewAllComponent implements OnInit {
  commClasses: ICommClass[] = [];
  searchedCommClasses: ICommClass[] = [];
  filteredCommClasses: ICommClass[] = [];
  finalCommClasses: ICommClass[] = [];
  searchValue: string = "";
  filterValue: string = "all";

  constructor(private commClassService: CommClassService) {
    
  }

  ngOnInit(): void {
    this.getCommClasses();
  }

  getCommClasses(): void {
    this.commClassService.getCommClasses().subscribe({
      next: (response) => {
        this.commClasses = response;
        this.filteredCommClasses = response;
        this.searchedCommClasses = response;
      }
    });
  }

  search(value: string) {
    this.searchValue = value;
    if (this.searchValue.length > 2) {
      this.searchedCommClasses = this.commClasses.filter((commClass) => {
        return commClass.commClassName?.toLowerCase().includes(this.searchValue.toLowerCase());
      })
    } else if (this.searchValue.length == 0) {
      this.searchedCommClasses = this.commClasses;
    }
    this.filter(this.filterValue);
  }

  filter(value: string) {
    this.filterValue = value;
    this.filteredCommClasses = this.searchedCommClasses.filter((commClass) => {
      if (this.filterValue == "all") {
        return commClass;
      } else {
        return commClass.commClassStatus?.toLowerCase() == this.filterValue.toLowerCase();
      }
    });
  }

  log(value: string) {
    console.log(value);
  }
}
