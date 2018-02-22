import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { InvestmentMySuffix } from './investment-my-suffix.model';
import { InvestmentMySuffixPopupService } from './investment-my-suffix-popup.service';
import { InvestmentMySuffixService } from './investment-my-suffix.service';
import { ProjectMySuffix, ProjectMySuffixService } from '../project-my-suffix';

@Component({
    selector: 'jhi-investment-my-suffix-dialog',
    templateUrl: './investment-my-suffix-dialog.component.html'
})
export class InvestmentMySuffixDialogComponent implements OnInit {

    investment: InvestmentMySuffix;
    isSaving: boolean;

    projects: ProjectMySuffix[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private investmentService: InvestmentMySuffixService,
        private projectService: ProjectMySuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.projectService.query()
            .subscribe((res: HttpResponse<ProjectMySuffix[]>) => { this.projects = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.investment.id !== undefined) {
            this.subscribeToSaveResponse(
                this.investmentService.update(this.investment));
        } else {
            this.subscribeToSaveResponse(
                this.investmentService.create(this.investment));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<InvestmentMySuffix>>) {
        result.subscribe((res: HttpResponse<InvestmentMySuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: InvestmentMySuffix) {
        this.eventManager.broadcast({ name: 'investmentListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackProjectById(index: number, item: ProjectMySuffix) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-investment-my-suffix-popup',
    template: ''
})
export class InvestmentMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private investmentPopupService: InvestmentMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.investmentPopupService
                    .open(InvestmentMySuffixDialogComponent as Component, params['id']);
            } else {
                this.investmentPopupService
                    .open(InvestmentMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
