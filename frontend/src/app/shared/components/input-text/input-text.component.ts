import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-input-text',
  templateUrl: './input-text.component.html',
  styleUrls: ['./input-text.component.css']
})
export class InputTextComponent {
  input: string = "";

  @Output()
  inputChanged: EventEmitter<string> = new EventEmitter<string>();

  onInputChanged() {
    this.inputChanged.emit(this.input);
  }
}
