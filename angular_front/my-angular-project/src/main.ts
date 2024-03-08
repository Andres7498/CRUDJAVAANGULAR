import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';

getData(): Observable<any[]> {
  return this.http.get<BaseResponse>(${url}persona/)
    .pipe(
      map((res) => {
        res = Object.assign(new BaseResponse(), res);
        if (res.success && res.parameters != null) {
          return JSON.parse(res);
        }
        else
          throw Error(res.message);
      }),
      catchError(err => {
        return throwError(() => err);
      })
  )
} 