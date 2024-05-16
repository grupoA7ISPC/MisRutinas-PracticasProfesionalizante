import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-form-clase',
  templateUrl: './form-clase.component.html',
  styleUrls: ['./form-clase.component.css']
})
export class FormClaseComponent {
  public form;

  constructor(private formBuilder: FormBuilder) {
    this.form = this.formBuilder.group({
      name: ['', [Validators.required, Validators.maxLength(45), Validators.minLength(1)]],
      price:  [0, [Validators.required, Validators.max(10000000), Validators.min(0)]],
      description:  ['', [Validators.required, Validators.maxLength(400), Validators.minLength(1)]],
      image:  ['', [Validators.required]],
      duration:  ['', [Validators.required, Validators.maxLength(150), Validators.minLength(1)]]
    });
  }

  onSubmit(e: Event) {
    e.preventDefault();
    console.log(this.form.value);
  }
}
