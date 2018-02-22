import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { CrowdfundingWalletMySuffix } from './crowdfunding-wallet-my-suffix.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<CrowdfundingWalletMySuffix>;

@Injectable()
export class CrowdfundingWalletMySuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/crowdfunding-wallets';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/crowdfunding-wallets';

    constructor(private http: HttpClient) { }

    create(crowdfundingWallet: CrowdfundingWalletMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(crowdfundingWallet);
        return this.http.post<CrowdfundingWalletMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(crowdfundingWallet: CrowdfundingWalletMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(crowdfundingWallet);
        return this.http.put<CrowdfundingWalletMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<CrowdfundingWalletMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<CrowdfundingWalletMySuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<CrowdfundingWalletMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CrowdfundingWalletMySuffix[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<CrowdfundingWalletMySuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<CrowdfundingWalletMySuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CrowdfundingWalletMySuffix[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: CrowdfundingWalletMySuffix = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<CrowdfundingWalletMySuffix[]>): HttpResponse<CrowdfundingWalletMySuffix[]> {
        const jsonResponse: CrowdfundingWalletMySuffix[] = res.body;
        const body: CrowdfundingWalletMySuffix[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to CrowdfundingWalletMySuffix.
     */
    private convertItemFromServer(crowdfundingWallet: CrowdfundingWalletMySuffix): CrowdfundingWalletMySuffix {
        const copy: CrowdfundingWalletMySuffix = Object.assign({}, crowdfundingWallet);
        return copy;
    }

    /**
     * Convert a CrowdfundingWalletMySuffix to a JSON which can be sent to the server.
     */
    private convert(crowdfundingWallet: CrowdfundingWalletMySuffix): CrowdfundingWalletMySuffix {
        const copy: CrowdfundingWalletMySuffix = Object.assign({}, crowdfundingWallet);
        return copy;
    }
}
