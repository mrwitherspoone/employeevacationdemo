import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkformComponent } from './workform.component';

describe('WorkformComponent', () => {
  let component: WorkformComponent;
  let fixture: ComponentFixture<WorkformComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WorkformComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WorkformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
