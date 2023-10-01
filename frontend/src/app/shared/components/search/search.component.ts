import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {
  name: string = "";
  filterValue: string = "";

  @Output()
  searchPressed: EventEmitter<string> = new EventEmitter<string>();

  @Output()
  filterChanged: EventEmitter<string> = new EventEmitter<string>();

  onInput(value: string) {
    this.name = value;
  }

  search() {
    if (this.name.length < 3 && this.name.length !== 0) {
      alert("Must input at least 3 characters")
    } else {
      this.searchPressed.emit(this.name);
    }
  }

  onFilterChanged(value: string) {
    this.filterValue = value;
    this.filterChanged.emit(this.filterValue);
  }
}
