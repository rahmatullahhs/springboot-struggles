import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddinvoiceComponent } from './addinvoice.component';

describe('AddinvoiceComponent', () => {
  let component: AddinvoiceComponent;
  let fixture: ComponentFixture<AddinvoiceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddinvoiceComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddinvoiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
