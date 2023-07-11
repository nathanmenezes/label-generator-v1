import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuotesInputComponent } from './quotes-input.component';

describe('QuotesInputComponent', () => {
  let component: QuotesInputComponent;
  let fixture: ComponentFixture<QuotesInputComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [QuotesInputComponent]
    });
    fixture = TestBed.createComponent(QuotesInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
