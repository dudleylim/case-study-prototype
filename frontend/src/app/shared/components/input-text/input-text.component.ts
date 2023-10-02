import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-input-text',
  templateUrl: './input-text.component.html',
  styleUrls: ['./input-text.component.css']
})
export class InputTextComponent {
  input: string = "";

  @Input()
  placeholder: string = "";

  @Input()
  value: string = "";

  @Output()
  inputChanged: EventEmitter<string> = new EventEmitter<string>();

  onInputChanged() {
    this.inputChanged.emit(this.input);
  }
}
