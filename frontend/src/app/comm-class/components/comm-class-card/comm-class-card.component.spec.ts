import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommClassCardComponent } from './comm-class-card.component';

describe('CommClassCardComponent', () => {
  let component: CommClassCardComponent;
  let fixture: ComponentFixture<CommClassCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CommClassCardComponent]
    });
    fixture = TestBed.createComponent(CommClassCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
