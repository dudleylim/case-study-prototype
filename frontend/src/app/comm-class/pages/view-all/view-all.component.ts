import { Component, OnInit } from '@angular/core';
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
    
  }

  filter(value: string) {
    this.filterValue = value;
    
  }

  log(value: string) {
    console.log(value);
  }
}
