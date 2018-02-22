/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { NuoTestModule } from '../../../test.module';
import { ProjectMySuffixDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/project-my-suffix/project-my-suffix-delete-dialog.component';
import { ProjectMySuffixService } from '../../../../../../main/webapp/app/entities/project-my-suffix/project-my-suffix.service';

describe('Component Tests', () => {

    describe('ProjectMySuffix Management Delete Component', () => {
        let comp: ProjectMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<ProjectMySuffixDeleteDialogComponent>;
        let service: ProjectMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [NuoTestModule],
                declarations: [ProjectMySuffixDeleteDialogComponent],
                providers: [
                    ProjectMySuffixService
                ]
            })
            .overrideTemplate(ProjectMySuffixDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ProjectMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProjectMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
