import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContaceComponent } from './contace.component';

describe('ContaceComponent', () => {
  let component: ContaceComponent;
  let fixture: ComponentFixture<ContaceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContaceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ContaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
