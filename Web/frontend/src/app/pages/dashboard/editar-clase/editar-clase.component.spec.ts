import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarClaseComponent } from './editar-clase.component';

describe('EditarClaseComponent', () => {
  let component: EditarClaseComponent;
  let fixture: ComponentFixture<EditarClaseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditarClaseComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditarClaseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
