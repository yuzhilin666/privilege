import { Component, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'demo',
  templateUrl: './demo.component.html',
  styleUrls: ['./demo.component.css'],
  encapsulation: ViewEncapsulation.Emulated
})
export class DemoComponent {
  message = 'I am demo';
}
