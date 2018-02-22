/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NuoTestModule } from '../../../test.module';
import { ProjectMySuffixComponent } from '../../../../../../main/webapp/app/entities/project-my-suffix/project-my-suffix.component';
import { ProjectMySuffixService } from '../../../../../../main/webapp/app/entities/project-my-suffix/project-my-suffix.service';
import { ProjectMySuffix } from '../../../../../../main/webapp/app/entities/project-my-suffix/project-my-suffix.model';

describe('Component Tests', () => {

    describe('ProjectMySuffix Management Component', () => {
        let comp: ProjectMySuffixComponent;
        let fixture: ComponentFixture<ProjectMySuffixComponent>;
        let service: ProjectMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [NuoTestModule],
                declarations: [ProjectMySuffixComponent],
                providers: [
                    ProjectMySuffixService
                ]
            })
            .overrideTemplate(ProjectMySuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ProjectMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProjectMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new ProjectMySuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.projects[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
