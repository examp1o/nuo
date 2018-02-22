import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { ProjectMySuffix } from './project-my-suffix.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<ProjectMySuffix>;

@Injectable()
export class ProjectMySuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/projects';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/projects';

    constructor(private http: HttpClient) { }

    create(project: ProjectMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(project);
        return this.http.post<ProjectMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(project: ProjectMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(project);
        return this.http.put<ProjectMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ProjectMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<ProjectMySuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<ProjectMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<ProjectMySuffix[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<ProjectMySuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<ProjectMySuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<ProjectMySuffix[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: ProjectMySuffix = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<ProjectMySuffix[]>): HttpResponse<ProjectMySuffix[]> {
        const jsonResponse: ProjectMySuffix[] = res.body;
        const body: ProjectMySuffix[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to ProjectMySuffix.
     */
    private convertItemFromServer(project: ProjectMySuffix): ProjectMySuffix {
        const copy: ProjectMySuffix = Object.assign({}, project);
        return copy;
    }

    /**
     * Convert a ProjectMySuffix to a JSON which can be sent to the server.
     */
    private convert(project: ProjectMySuffix): ProjectMySuffix {
        const copy: ProjectMySuffix = Object.assign({}, project);
        return copy;
    }
}
