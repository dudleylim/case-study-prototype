import { TestBed } from '@angular/core/testing';

import { CommClassService } from './comm-class.service';

describe('CommClassService', () => {
  let service: CommClassService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CommClassService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
