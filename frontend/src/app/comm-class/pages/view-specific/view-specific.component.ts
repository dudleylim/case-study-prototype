import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';

@Component({
  selector: 'app-view-specific',
  templateUrl: './view-specific.component.html',
  styleUrls: ['./view-specific.component.css']
})
export class ViewSpecificComponent implements OnInit {
  id: Number = 0;

  constructor(private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.id = +Number(params.get('id'));
    });
    // this.id = Number(this.route.snapshot.paramMap.get('id'));
  }
}
