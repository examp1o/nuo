import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { BulletinMySuffix } from './bulletin-my-suffix.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<BulletinMySuffix>;

@Injectable()
export class BulletinMySuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/bulletins';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/bulletins';

    constructor(private http: HttpClient) { }

    create(bulletin: BulletinMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(bulletin);
        return this.http.post<BulletinMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(bulletin: BulletinMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(bulletin);
        return this.http.put<BulletinMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<BulletinMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<BulletinMySuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<BulletinMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<BulletinMySuffix[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<BulletinMySuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<BulletinMySuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<BulletinMySuffix[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: BulletinMySuffix = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<BulletinMySuffix[]>): HttpResponse<BulletinMySuffix[]> {
        const jsonResponse: BulletinMySuffix[] = res.body;
        const body: BulletinMySuffix[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to BulletinMySuffix.
     */
    private convertItemFromServer(bulletin: BulletinMySuffix): BulletinMySuffix {
        const copy: BulletinMySuffix = Object.assign({}, bulletin);
        return copy;
    }

    /**
     * Convert a BulletinMySuffix to a JSON which can be sent to the server.
     */
    private convert(bulletin: BulletinMySuffix): BulletinMySuffix {
        const copy: BulletinMySuffix = Object.assign({}, bulletin);
        return copy;
    }
}
