import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.css']
})
export class ButtonComponent implements OnInit {
  classesList: string[] = [];
  @Input() bgColor!: string;
  
  ngOnInit(): void {
    this.classesList.push(this.bgColor);
    let textColor: string;
    if (this.bgColor == "bg-secondary" || this.bgColor == "bg-success" || this.bgColor == "bg-error") {
      textColor = "text-background";
    } else {
      textColor = "text-secondary";
    }
    this.classesList.push(textColor);
  }
}
