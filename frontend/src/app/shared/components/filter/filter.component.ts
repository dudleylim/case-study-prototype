import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent {
  filterValue: string = "all";

  @Output()
  filterChanged: EventEmitter<string> = new EventEmitter<string>();

  onFilterChanged() {
    this.filterChanged.emit(this.filterValue);
  }
}
