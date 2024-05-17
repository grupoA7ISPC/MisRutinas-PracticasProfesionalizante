import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabClaseComponent } from './tab-clase.component';

describe('TabClaseComponent', () => {
  let component: TabClaseComponent;
  let fixture: ComponentFixture<TabClaseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TabClaseComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TabClaseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
