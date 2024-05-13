import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardIntegranteComponent } from './card-integrante.component';

describe('CardIntegranteComponent', () => {
  let component: CardIntegranteComponent;
  let fixture: ComponentFixture<CardIntegranteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CardIntegranteComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CardIntegranteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
