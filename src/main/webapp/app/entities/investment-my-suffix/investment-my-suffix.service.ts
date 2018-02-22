import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { InvestmentMySuffix } from './investment-my-suffix.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<InvestmentMySuffix>;

@Injectable()
export class InvestmentMySuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/investments';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/investments';

    constructor(private http: HttpClient) { }

    create(investment: InvestmentMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(investment);
        return this.http.post<InvestmentMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(investment: InvestmentMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(investment);
        return this.http.put<InvestmentMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<InvestmentMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<InvestmentMySuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<InvestmentMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<InvestmentMySuffix[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<InvestmentMySuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<InvestmentMySuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<InvestmentMySuffix[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: InvestmentMySuffix = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<InvestmentMySuffix[]>): HttpResponse<InvestmentMySuffix[]> {
        const jsonResponse: InvestmentMySuffix[] = res.body;
        const body: InvestmentMySuffix[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to InvestmentMySuffix.
     */
    private convertItemFromServer(investment: InvestmentMySuffix): InvestmentMySuffix {
        const copy: InvestmentMySuffix = Object.assign({}, investment);
        return copy;
    }

    /**
     * Convert a InvestmentMySuffix to a JSON which can be sent to the server.
     */
    private convert(investment: InvestmentMySuffix): InvestmentMySuffix {
        const copy: InvestmentMySuffix = Object.assign({}, investment);
        return copy;
    }
}
