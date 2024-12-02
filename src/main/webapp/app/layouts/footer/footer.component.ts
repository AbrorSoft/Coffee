import { Component } from '@angular/core';
import { TranslateDirective } from 'app/shared/language';

@Component({
  standalone: true,
  selector: 'jhi-footer',
  templateUrl: './footer.component.html',
  imports: [TranslateDirective],
  styleUrl: 'footer.component.scss'
})
export default class FooterComponent {}
