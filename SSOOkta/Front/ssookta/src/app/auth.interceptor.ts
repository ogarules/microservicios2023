
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http'
import { Inject, Injectable } from "@angular/core";

import { OKTA_AUTH } from "@okta/okta-angular"
import { OktaAuth } from '@okta/okta-auth-js';
import { Observable, from } from 'rxjs';


@Injectable()
export class AuthInterceptor implements HttpInterceptor{

    
    constructor(@Inject(OKTA_AUTH) private oktaAuth: OktaAuth){
    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return from(this.handleAccess(req, next));
    }

    private async handleAccess(request: HttpRequest<any>, next: HttpHandler) : Promise<HttpEvent<any>> {
        const allowedOrigins = ['http://localhost']
        if(allowedOrigins.some(url => request.urlWithParams.includes(url))){
            const accessToken = this.oktaAuth.getAccessToken();

            request = request.clone({
                setHeaders: {
                    Authorization: 'Bearer ' + accessToken
                }
            });
        }

        return next.handle(request).toPromise();
    }
}