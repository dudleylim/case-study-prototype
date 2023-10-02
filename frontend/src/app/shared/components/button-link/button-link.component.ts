import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-button-link',
  templateUrl: './button-link.component.html',
  styleUrls: ['./button-link.component.css']
})
export class ButtonLinkComponent {
  classesList: string[] = [];
  @Input() bgColor!: string;
  @Input() route!: string;
  
  ngOnInit(): void {
    this.classesList.push(this.bgColor);
    let textColor: string;
    if (this.bgColor == "bg-secondary") {
      textColor = "text-background";
    } else {
      textColor = "text-secondary";
    }
    this.classesList.push(textColor);
  }
}
