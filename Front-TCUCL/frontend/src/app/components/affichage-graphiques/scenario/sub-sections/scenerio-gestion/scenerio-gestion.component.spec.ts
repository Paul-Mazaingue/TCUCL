import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScenerioGestionComponent } from './scenerio-gestion.component';

describe('ScenerioGestionComponent', () => {
  let component: ScenerioGestionComponent;
  let fixture: ComponentFixture<ScenerioGestionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ScenerioGestionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScenerioGestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
