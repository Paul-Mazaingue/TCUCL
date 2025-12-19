import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

const computeAcademicYear = (): number => {
  const now = new Date();
  // Mois: 0 = janvier, 7 = août. On coupe au 31/08 inclus.
  const cutoff = new Date(now.getFullYear(), 7, 31, 23, 59, 59, 999);
  return now.getTime() <= cutoff.getTime() ? now.getFullYear() : now.getFullYear() + 1;
};

@Injectable({ providedIn: 'root' })
export class AnneeService {
  private _selectedYear = new BehaviorSubject<number>(computeAcademicYear());
  selectedYear$ = this._selectedYear.asObservable();

  setSelectedYear(year: number): void {
    this._selectedYear.next(year);
  }

  getSelectedYear(): number {
    return this._selectedYear.getValue();
  }

  getCurrentAcademicYear(): number {
    return computeAcademicYear();
  }
}
