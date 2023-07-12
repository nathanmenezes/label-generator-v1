import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReplaceComponent } from './replace.component';

describe('ReplaceComponent', () => {
  let component: ReplaceComponent;
  let fixture: ComponentFixture<ReplaceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReplaceComponent]
    });
    fixture = TestBed.createComponent(ReplaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
