import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddcatComponent } from './addcat.component';

describe('AddcatComponent', () => {
  let component: AddcatComponent;
  let fixture: ComponentFixture<AddcatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddcatComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddcatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
