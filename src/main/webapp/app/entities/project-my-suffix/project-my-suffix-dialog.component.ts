import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ProjectMySuffix } from './project-my-suffix.model';
import { ProjectMySuffixPopupService } from './project-my-suffix-popup.service';
import { ProjectMySuffixService } from './project-my-suffix.service';

@Component({
    selector: 'jhi-project-my-suffix-dialog',
    templateUrl: './project-my-suffix-dialog.component.html'
})
export class ProjectMySuffixDialogComponent implements OnInit {

    project: ProjectMySuffix;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private projectService: ProjectMySuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.project.id !== undefined) {
            this.subscribeToSaveResponse(
                this.projectService.update(this.project));
        } else {
            this.subscribeToSaveResponse(
                this.projectService.create(this.project));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ProjectMySuffix>>) {
        result.subscribe((res: HttpResponse<ProjectMySuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: ProjectMySuffix) {
        this.eventManager.broadcast({ name: 'projectListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-project-my-suffix-popup',
    template: ''
})
export class ProjectMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private projectPopupService: ProjectMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.projectPopupService
                    .open(ProjectMySuffixDialogComponent as Component, params['id']);
            } else {
                this.projectPopupService
                    .open(ProjectMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
