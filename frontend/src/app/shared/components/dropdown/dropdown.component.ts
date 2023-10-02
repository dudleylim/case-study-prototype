import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-dropdown',
  templateUrl: './dropdown.component.html',
  styleUrls: ['./dropdown.component.css']
})
export class DropdownComponent {
  @Input() id!: String;
  input: String = "";

  @Input() value: any = "";

  @Output()
  valueChanged: EventEmitter<String> = new EventEmitter<String>();

  onValueChanged() {
    this.valueChanged.emit(this.input);
  }
}
