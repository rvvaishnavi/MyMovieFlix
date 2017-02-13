/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { TopratedMoviesService } from './toprated-movies.service';

describe('TopratedMoviesService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TopratedMoviesService]
    });
  });

  it('should ...', inject([TopratedMoviesService], (service: TopratedMoviesService) => {
    expect(service).toBeTruthy();
  }));
});
